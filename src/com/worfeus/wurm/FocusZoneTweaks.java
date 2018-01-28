package com.worfeus.wurm;

/**
 * Created by Wim on 1/25/2018.
 */



import com.wurmonline.server.villages.Villages;


import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
//import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.PlayerLoginListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
//import org.gotti.wurmunlimited.modloader.interfaces.MessagePolicy;


public class FocusZoneTweaks implements WurmServerMod, Initable, PreInitable, Configurable {
    private static final Logger logger = Logger.getLogger("FocusZoneTweaks");
    String propertyLines[] = new String[10];


    public FocusZoneTweaks(){

    }

    public void configure(Properties properties) {

    }

    public void preInit(){
        // Allow building inside a NON PVP or a PVP focus zone
        try{
            ClassPool e = HookManager.getInstance().getClassPool();
            CtClass ctVillages;
            ctVillages = e.getCtClass("com.wurmonline.server.villages.Villages");

            ctVillages.getDeclaredMethod("isFocusZoneBlocking").setBody("{return \"\" ;}");


            // Disable token draining in NON PVP focus zones
/*
            VolaTile villtile = Zones.getOrCreateTile(rtx, rty, true);
            Village vill = villtile.getVillage();
            if (vill != null) {
                return;
            }

            // Added by Worfeus
            boolean isPvpZone = villtile.isInPvPZone();
            if (!isPvpZone){
                return;
            }
            // end of Worfeus Add*/

        } catch (Throwable var6) {
            logException("Error loading FocusZoneTweaks mod - build in non-PVP", var6);
            throw new RuntimeException(var6);
        }

        // Disable token draining in NON PVP focus zones
        //try{
          /*  ClassPool e = HookManager.getInstance().getClassPool();
            CtMethod ctMethodDrain;
            ctMethodDrain = e.getCtClass("com.wurmonline.server.behaviours.Methods").getDeclaredMethod("drainCoffers");

            ctMethodDrain.instrument(new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals("com.wurmonline.server.behaviours.Methods")
                            && m.getMethodName().equals("performer.getCommunicator().sendNormalServerMessage(\"You must be on the same floor level as the \" + token.getName() + \".\");\n" +
                            "                return true;\n" +
                            "            }")) {
                                    m.replace("performer.getCommunicator().sendNormalServerMessage(\"You must be on the same floor level as the \" + token.getName() + \".\");\n" +
                            "                return true;\n"+
                            "            }"+
                                "\n if (!village.isInPVPZone()) {\n"+
                                        "performer.getCommunicator().sendNormalServerMessage(\"You cannot drain a deed in a PVE Zone! \");" +
                                        "return true;\n"+
                                        "}\n"
                        );
                    }
                }
            });
        } catch (Throwable var6) {
        logException("Error loading FocusZoneTweaks mod - disable drain in non-PVP", var6);
        throw new RuntimeException(var6);
    }*/


    }

    public void init(){


    }


    public static void logException(String msg, Throwable e) {
        if(logger != null) {
            logger.log(Level.SEVERE, msg, e);
        }

    }

    public static void logWarning(String msg) {
        if(logger != null) {
            logger.log(Level.WARNING, msg);
        }

    }

    public static void logInfo(String msg) {
        if(logger != null) {
            logger.log(Level.INFO, msg);
        }

    }

}
