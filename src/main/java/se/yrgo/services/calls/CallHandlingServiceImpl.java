package se.yrgo.services.calls;

import java.util.Collection;

import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

public class CallHandlingServiceImpl implements CallHandlingService {

    CustomerManagementService customerManagementService;
    DiaryManagementService diaryManagementService;

    public CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService) {
        this.customerManagementService = customerManagementService;
        this.diaryManagementService = diaryManagementService;    
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        customerManagementService.recordCall(customerId, newCall);

        for(Action a : actions) {
            diaryManagementService.recordAction(a);
        }
    }
}
