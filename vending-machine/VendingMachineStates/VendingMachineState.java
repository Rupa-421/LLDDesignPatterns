package VendingMachineState;

public interface VendingMachineState {
    String getStateName();
    VendingMachineState next(VendingMachineContext context);
}