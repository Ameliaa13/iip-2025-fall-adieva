import elevator.api.*;
import elevator.impl.cmd.ElevatorCore;
import elevator.impl.model.Elevator;

public class MyElevatorService extends ElevatorService {

    private final int countOfFloors;
    private boolean[] callsOnFloors;
    private RunningStatus lastDirection;
    private RunningStatus currentStatus;
    int count = 0;

    public MyElevatorService(int countOfFloors) {
        this.countOfFloors = countOfFloors;
        callsOnFloors = new boolean[countOfFloors + 1];
        currentStatus = RunningStatus.WAITING;
        lastDirection = RunningStatus.WAITING;
    }

    @Override
    public void handleInsideKeyPress(InsideKeyInput info) {
        if(info.getType() == InsideKeyType.FLOOR_NUMBER) {
            callsOnFloors[info.getFloorNumber()] = true;
        }
        if(info.getType() == InsideKeyType.SIGNAL_DOOR_OPEN && coreModule.getElevator(0).getElevatorState() == ElevatorState.WAITING_CLOSED && currentStatus != RunningStatus.OPENING_DOORS) {
            currentStatus = RunningStatus.OPENING_DOORS;
            coreModule.getElevator(0).openDoors();
        }

        if(info.getType() == InsideKeyType.SIGNAL_DOOR_CLOSE && coreModule.getElevator(0).getElevatorState() == ElevatorState.WAITING_OPENED && currentStatus != RunningStatus.CLOSING_DOORS) {
            currentStatus = RunningStatus.CLOSING_DOORS;
            coreModule.getElevator(0).closeDoors();
        }

        if(info.getType() == InsideKeyType.AC_CHANGE && count%2==0){
            coreModule.getElevator(0).setAirConditioningEnabled(true);
            count ++;
        }else if(info.getType() == InsideKeyType.AC_CHANGE && count%2 !=0){
            coreModule.getElevator(0).setAirConditioningEnabled(false);
            count++;
        }
    }

    @Override
    public void handleOutsideKeyPress(OutsideKeyInput info) {
        if(info.getDirection() == OutsideKeyDirection.CALL) {
            callsOnFloors[info.getFloor()] = true;
        }
    }

    @Override
    protected void loop() {
        ElevatorCore elevator = coreModule.getElevator(0);

        if (currentStatus == RunningStatus.WAITING) {
            boolean found = false;

            if (lastDirection == RunningStatus.MOVING_UP) {
                for (int i = elevator.getElevatorFloor() + 1; i <= countOfFloors; i++) {
                    if (callsOnFloors[i]) {
                        currentStatus = RunningStatus.MOVING_UP;
                        lastDirection = RunningStatus.MOVING_UP;
                        found = true;
                        break;
                    }
                }
            }
            if (!found && lastDirection == RunningStatus.MOVING_DOWN) {
                for (int i = elevator.getElevatorFloor() - 1; i >= 0; i--) {
                    if (callsOnFloors[i]) {
                        currentStatus = RunningStatus.MOVING_DOWN;
                        lastDirection = RunningStatus.MOVING_DOWN;
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                for (int i = 0; i <= countOfFloors; i++) {
                    if (callsOnFloors[i]) {
                        if (i > elevator.getElevatorFloor()) {
                            currentStatus = RunningStatus.MOVING_UP;
                            lastDirection = RunningStatus.MOVING_UP;
                        } else if (i < elevator.getElevatorFloor()) {
                            currentStatus = RunningStatus.MOVING_DOWN;
                            lastDirection = RunningStatus.MOVING_DOWN;
                        } else {
                            callsOnFloors[i] = false;
                            currentStatus = RunningStatus.OPENING_DOORS;
                            elevator.openDoors();

                        }
                        break;
                    }
                }
            }
        }
         else if(currentStatus == RunningStatus.MOVING_UP) {
            boolean flag = false;
            for(int i = elevator.getElevatorFloor(); i < countOfFloors; i++) {
                if(callsOnFloors[i]) {
                    flag = true;
                    break;
                }
            }
            if(!flag) currentStatus = RunningStatus.WAITING;

            if(elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                if(callsOnFloors[elevator.getElevatorFloor()]) {
                    callsOnFloors[elevator.getElevatorFloor()] = false;
                    currentStatus = RunningStatus.OPENING_DOORS;
                    elevator.openDoors();
                    return;
                }

                elevator.moveToFloor(elevator.getElevatorFloor() + 1);
                coreModule.getElevator(0).setStringOnDisplay(String.valueOf(elevator.getElevatorFloor()+1)+" "+"↑");
            }
        } else if(currentStatus == RunningStatus.MOVING_DOWN) {
            boolean flag = false;
            for(int i = elevator.getElevatorFloor(); i >= 0; i--) {
                if(callsOnFloors[i]) {
                    flag = true;
                    break;
                }
            }
            if(!flag) currentStatus = RunningStatus.WAITING;


            if(elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                if (callsOnFloors[elevator.getElevatorFloor()]) {
                    callsOnFloors[elevator.getElevatorFloor()] = false;
                    currentStatus = RunningStatus.OPENING_DOORS;
                    elevator.openDoors();
                    return;
                }

                elevator.moveToFloor(elevator.getElevatorFloor() - 1);
                coreModule.getElevator(0).setStringOnDisplay(String.valueOf(elevator.getElevatorFloor()-1)+" " + "↓");
            }
        } else if(currentStatus == RunningStatus.OPENING_DOORS) {
            if(elevator.getElevatorState() == ElevatorState.WAITING_OPENED) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                currentStatus = RunningStatus.CLOSING_DOORS;
                if (coreModule.getElevator(0).getElevatorState() != ElevatorState.DOORS_CLOSING)
                    elevator.closeDoors();

            }
        } else if(currentStatus == RunningStatus.CLOSING_DOORS) {
            if(elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                currentStatus = RunningStatus.WAITING;
            }
        }

        if(coreModule.getElevator(0).getElevatorState() == ElevatorState.WAITING_OPENED){
            //ну я хз, лучше ничего не придумала, пришлось гуглить. Не бейте пж 0_0
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentStatus = RunningStatus.CLOSING_DOORS;
            if (coreModule.getElevator(0).getElevatorState() != ElevatorState.DOORS_CLOSING)
                coreModule.getElevator(0).closeDoors();

        }
    }
}
