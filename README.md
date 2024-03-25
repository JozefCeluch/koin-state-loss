This is a bare minimum example of `koinNavGraphViewModel` failing to restore the state correctly
when the VM uses a `SavedStateHandle` (or at least it doesn't behave in the way I would expect).

To simulate the case where the activity gets killed by the system you should enable the **Don't keep
activities** option in the Developer settings which makes the behavior reproducible.

# Expectations:

There are 2 fragments in the flow, `FirstFragment` and `SecondFragment`. Each fragment has its own
view model but there's also `SharedViewModel` for the state that's shared across the two fragment
and should be valid for the lifecycle of the flow.

The shared state is passed to the first fragment as an argument, then stored in
the `SharedViewModle`, and displayed on the screen in both fragments.

The expected behavior is that the state is persisted for the lifecycle of the flow (navigation
subgraph) and that it will be restored after the application comes back from the background where it
was killed by the system.

The behavior is correct in the `FirstFragment` because there the state gets restored from the
navigation arguments but it should be restored in the `SecondFragment` too.

I also added a log and a toast that are shown when the shared VM is cleared to understand its
lifecycle.

# Behavior:

1. `koinNavGraphViewModel`
    - the state is lost on the `SecondFragment` - ("NO STATE" fallback value is displayed)
    - `SharedViewModel` is cleared as expected when the flow is closed

2. `activityViewModel`
    - the state is restored correctly on the `SecondFragment`
    - `SharedViewModel` is **not** cleared when the flow is closed, it's only cleared when the app
      goes to background

3. `activityViewModel` with custom producers
    - the state is restored correctly on the `SecondFragment`
    - `SharedViewModel` is cleared as expected when the flow is closed

```
val sharedViewModel by activityViewModel<SharedViewModel>(
     ownerProducer = { findNavController().getViewModelStoreOwner(R.id.modifiedactivityvm_shared_state_flow) },
     extrasProducer = { findNavController().getBackStackEntry(R.id.modifiedactivityvm_shared_state_flow).defaultViewModelCreationExtras }
)
```

To me the current behavior of `koinNavGraphViewModel` seems incorrect and I would expect it to
behave as the case no. 3. 