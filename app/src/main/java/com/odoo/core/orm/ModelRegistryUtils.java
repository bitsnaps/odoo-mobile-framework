package com.odoo.core.orm;

import android.content.Context;
import android.util.Log;

import com.odoo.App;
import com.odoo.addons.projects.models.ProjectTask;
import com.odoo.core.support.OUser;
import com.odoo.core.utils.logger.OLog;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class ModelRegistryUtils {


    private HashMap<String, Class<? extends OModel>> models = new HashMap<>();

    public void makeReady(Context context) {
            try {

                DexFile dexFile = new DexFile(context.getPackageCodePath());
                for (Enumeration<String> item = dexFile.entries(); item.hasMoreElements(); ) {
                    String element = item.nextElement();
                    if (element.startsWith(App.class.getPackage().getName())) {
                        parseElement(context, element);
                    }
                }

                /*/ rs
                for (String element :
                        Arrays.asList("Company", "Country", "CountryState", "Users", "Currency", "PartnerCategory", "Partner")) {
                    parseElement(context, "com.odoo.base.addons.res.Res" + element);
                }
                // ir
                for (String element :
                        Arrays.asList("Model","Attachment")) {
                    parseElement(context, "com.odoo.base.addons.ir.Ir" + element);
                }
                // mail
                for (String element :
                        Arrays.asList("Message","MessageSubType")) {
                    parseElement(context, "com.odoo.base.addons.mail.Mail" + element);
                }
                for (String element :
                        Arrays.asList("ProjectProject", "ProjectTask")) {
                    parseElement(context, "com.odoo.addons.projects.models." + element);
                }*/

                OLog.log("Registered: " + this.models.size()+" model(s).");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private void parseElement(Context context, String element) throws ClassNotFoundException {
        Class<? extends OModel> clsName = (Class<? extends OModel>) Class.forName(element);
        if (clsName != null && clsName.getSuperclass() != null &&
                OModel.class.isAssignableFrom(clsName.getSuperclass())) {
            String modelName = getModelName(context, clsName);
                        OLog.log("modelName: " + modelName);
            if (modelName != null) {
                this.models.put(modelName, clsName);
            }
        }
    }

    private String getModelName(Context context, Class cls) {
        try {
            Constructor constructor = cls.getConstructor(Context.class, OUser.class);
            OModel model = (OModel) constructor.newInstance(context, null);
            return model.getModelName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<? extends OModel> getModel(String modelName) {
        if (models.containsKey(modelName)) {
            return models.get(modelName);
        }
        return null;
    }

    public HashMap<String, Class<? extends OModel>> getModels() {
        return models;
    }
}
