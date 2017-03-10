package com.nhahv.lovecoupon.ui.shop.templatecreation;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.CouponTemplate;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class TemplateValidation {
    private final CouponTemplate mTemplate;

    public TemplateValidation(@NonNull CouponTemplate template) {
        mTemplate = template;
    }

    public void validation(@NonNull Callback callback) {
        if (!isNonNull(mTemplate.getValue())) {
            callback.onError(Error.AMOUNT);
            return;
        }
        if (!isNonNull(mTemplate.getContent())) {
            callback.onError(Error.CONTENT);
            return;
        }
        callback.onSuccess();
    }

    private boolean isNonNull(String text) {
        return text != null && !text.isEmpty();
    }

    public enum Error {
        AMOUNT, CONTENT
    }

    public interface Callback {
        void onSuccess();
        void onError(Error error);
    }
}
