package github.tornaco.android.thanox.magisk.bridge;

import com.elvishew.xlog.XLog;

import github.tornaco.android.thanox.magisk.bridge.proxy.SystemServiceRegistryHookInstaller;

public class ProcessHookInstaller implements ProcessHandler {

    @Override
    public void onSystemServerProcess() {
        XLog.d("ProcessHookInstaller onSystemServerProcess");
        SystemSeverProcessFrameworkHookInstaller.install(true);
    }

    @Override
    public void onAppProcess(String processName) {
        XLog.d("ProcessHookInstaller onAppProcess: %s", processName);
        // Hooks for Register.
        // It should run early on app process start.
        SystemServiceRegistryHookInstaller.install();
        onStartApplication(processName);
    }

    private void onStartApplication(String processName) {
        XLog.d("ProcessHookInstaller onStartApplication: %s", processName);
        AppProcessSystemServiceHookInstaller.install();
    }
}