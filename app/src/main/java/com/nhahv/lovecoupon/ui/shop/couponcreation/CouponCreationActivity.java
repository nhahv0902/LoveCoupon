package com.nhahv.lovecoupon.ui.shop.couponcreation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.databinding.ActivityCouponCreationBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_TEMPLATE;

public class CouponCreationActivity extends BaseActivity {
    private ActivityCouponCreationBinding mBinding;
    private CouponCreationViewModel mViewModel;

    public static Intent getCouponCreationIntent(Context context, CouponTemplate template) {
        Intent intent = new Intent(context, CouponCreationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_TEMPLATE, template);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coupon_creation);
        CouponTemplate template = getDataFromIntent();
        mViewModel = new CouponCreationViewModel(getApplicationContext(), template);
        mBinding.setViewModel(mViewModel);
        start(template);
    }

    private void start(CouponTemplate template) {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_coupon_creation, template.getValue()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public CouponTemplate getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return new CouponTemplate();
        return (CouponTemplate) bundle.getSerializable(BUNDLE_TEMPLATE);
    }
}
