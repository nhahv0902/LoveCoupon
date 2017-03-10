package com.nhahv.lovecoupon.ui.shop.templatecreation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityTemplateCreationBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

public class TemplateCreationActivity extends BaseActivity implements ITemplateCreationHandler {
    private ActivityTemplateCreationBinding mBinding;
    private TemplateCreationViewModel mViewModel;

    public static Intent getTemplateIntent(@NonNull Context context) {
        return new Intent(context, TemplateCreationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_template_creation);
        mViewModel = new TemplateCreationViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
        start();
    }

    @Override
    protected void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_template_creation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createTemplateSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public int getDuration() {
        return ((mBinding.spinnerMonth.getSelectedItemPosition() + 1) * 30);
    }
}
