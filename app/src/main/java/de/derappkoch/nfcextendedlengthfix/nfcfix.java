package de.derappkoch.nfcextendedlengthfix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class nfcfix implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        //XposedBridge.log("Loaded app: " + lpparam.packageName);

        if (!"com.android.nfc".equals(lpparam.packageName))
            return;

        //XposedBridge.log("NFCFIX : " + lpparam.packageName);
        findAndHookMethod("com.android.nfc.dhimpl.NativeNfcManager", lpparam.classLoader, "getMaxTransceiveLength", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
               // XposedBridge.log("NFCFIX : " + "getMaxTransceiveLength");
                int technology = (int)param.args[0];
                if(technology == 3 /* 3=TagTechnology.ISO_DEP */) {
                    param.setResult(2462);
                }
            }
        });

        findAndHookMethod("com.android.nfc.dhimpl.NativeNfcManager", lpparam.classLoader, "getExtendedLengthApdusSupported", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                //XposedBridge.log("NFCFIX : " + "getExtendedLengthApdusSupported");
                    param.setResult(true);

            }
        });
    }


}
