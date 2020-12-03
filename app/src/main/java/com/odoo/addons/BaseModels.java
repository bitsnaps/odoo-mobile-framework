package com.odoo.addons;

import android.content.Context;

import com.odoo.addons.projects.models.ProjectProject;
import com.odoo.addons.projects.models.ProjectTask;
import com.odoo.base.addons.ir.IrAttachment;
import com.odoo.base.addons.ir.IrModel;
import com.odoo.base.addons.mail.MailMessage;
import com.odoo.base.addons.mail.MailMessageSubType;
import com.odoo.base.addons.res.ResCompany;
import com.odoo.base.addons.res.ResCountry;
import com.odoo.base.addons.res.ResCountryState;
import com.odoo.base.addons.res.ResCurrency;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResPartnerCategory;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.support.OUser;

import java.util.ArrayList;
import java.util.List;

public class BaseModels {
    public static final String TAG = BaseModels.class.getSimpleName();

    public static List<OModel> baseModels(Context context, OUser user) {
        List<OModel> models = new ArrayList<>();

        // Project Models
        models.add(new ProjectProject(context, user));
        models.add(new ProjectTask(context, user));

        return models;
    }
}