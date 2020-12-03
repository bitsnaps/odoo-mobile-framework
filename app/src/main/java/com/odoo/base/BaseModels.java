package com.odoo.base;

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

//        models.add(new OdooNews(context, user));

        // ir
        models.add(new IrModel(context, user));
        models.add(new IrAttachment(context, user));

        // rs
        models.add(new ResPartner(context, user));
        models.add(new ResPartnerCategory(context, user));
        models.add(new ResUsers(context, user));
        models.add(new ResCompany(context, user));
        models.add(new ResCountry(context, user));
        models.add(new ResCountryState(context, user));
        models.add(new ResCurrency(context, user));

        models.add(new MailMessage(context, user));
        models.add(new MailMessageSubType(context, user));

        return models;
    }
}