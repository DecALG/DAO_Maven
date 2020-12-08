package mldn.factory;

import mldn.Service.EmpService;
import mldn.Service.impl.EmpServiceImpl;


public class ServiceFactory {
    public static EmpService getIEmpServiceInstance(){
        return new EmpServiceImpl();
    }
}
