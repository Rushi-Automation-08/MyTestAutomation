package common;

import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReportUtils {

    public void setTestCaseNameInAllure(ITestResult result) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            Test testAnnotation = method.getAnnotation(Test.class);
            if(testAnnotation != null && !testAnnotation.testName().isBlank()) {
                Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testAnnotation.testName()));
            } else {
                System.out.println("testName property is missing in @Test annotation of method: " + method.getName());
            }
        } finally {
          lock.unlock();
        }
    }
}
