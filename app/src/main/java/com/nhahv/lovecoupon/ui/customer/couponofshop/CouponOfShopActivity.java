package com.nhahv.lovecoupon.ui.customer.couponofshop;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.databinding.ActivityCouponOfShopBinding;
import org.parceler.Parcels;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_COUPON;

public class CouponOfShopActivity extends AppCompatActivity implements CouponOfShopHandler {
    private ActivityCouponOfShopBinding mBinding;
    private CouponOfShopViewModel mViewModel;

    public static Intent getIntent(Context context, CouponCustomer coupon) {
        Intent intent = new Intent(context, CouponOfShopActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_COUPON, Parcels.wrap(coupon));
        intent.putExtras(bundle);
        return intent;
    }

    private CouponCustomer getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(BUNDLE_COUPON) == null) {
            return new CouponCustomer();
        }
        return Parcels.unwrap(bundle.getParcelable(BUNDLE_COUPON));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coupon_of_shop);
        mViewModel = new CouponOfShopViewModel(getApplicationContext(), this, getDataFromIntent());
        mBinding.setViewModel(mViewModel);
    }

    @Override
    public void backPress() {
        onBackPressed();
    }

    @Override
    public void showDialog(Coupon coupon, int position) {
        new MaterialDialog.Builder(this).content(R.string.title_delete_cooupon)
                .contentColor(ContextCompat.getColor(this, R.color.color_grey_700))
                .positiveText(R.string.agree)
                .positiveColor(ContextCompat.getColor(this, R.color.color_blue_600))
                .onPositive((dialog, which) -> mViewModel.deleteCoupon(coupon, position))
                .show();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
