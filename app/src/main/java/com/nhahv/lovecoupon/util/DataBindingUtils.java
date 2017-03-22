package com.nhahv.lovecoupon.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.databinding.NavHeaderMainBinding;
import com.nhahv.lovecoupon.ui.MainViewModel;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.pickimage.image.ImagePickerViewModel;
import com.nhahv.lovecoupon.ui.shop.setting.SettingViewModel;
import com.nhahv.lovecoupon.ui.shop.setting.UserType;
import com.nhahv.lovecoupon.ui.widget.RecyclerViewHeader;
import com.rengwuxian.materialedittext.MaterialEditText;
import net.glxn.qrgen.android.QRCode;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ADMIN;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_SPAN;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class DataBindingUtils {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /*
    * bind image path file and url internet
    * */
    @BindingAdapter({ "bind:circleImage", "bind:error" })
    public static void setCircleImage(final ImageView view, String url, final Drawable error) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .placeholder(error)
                .dontAnimate()
                .into(view);
    }
    /*
    * bind SwipeRefreshLayout refresh
    * */

    @BindingAdapter({ "bind:onRefresh", "bind:refresh" })
    public static void onRefresh(SwipeRefreshLayout view, ViewModel viewModel,
            ObservableBoolean isShow) {
        /*
        * true is show refresh
        * false is hide refresh
        * */
        view.setColorSchemeResources(R.color.colorPrimary);
        view.setRefreshing(isShow.get());
        view.setOnRefreshListener(() -> {
            isShow.set(true);
            viewModel.loadData();
        });
    }

    /*
    * bind adapter of recycler with linearLayout
    * */
    @BindingAdapter({ "bind:adapter" })
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(adapter);
    }

    @BindingAdapter({ "bind:adapterLinear" })
    public static void setAdapterLinear(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        view.setAdapter(adapter);
    }

    /*
       * bind adapter of recycler with linearLayout
       * */
    @BindingAdapter({ "bind:gridAdapter" })
    public static void setGridAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(new GridLayoutManager(view.getContext(), DATA_SPAN));
        view.setAdapter(adapter);
    }

    /*
    * SettingFragment
    * */

    @BindingAdapter({ "bind:showPassword" })
    public static void setShowPassword(AppCompatImageView view, AppCompatEditText edit) {
        edit.setSelection(edit.getText().length());
        view.setOnClickListener(v -> {
            if (edit.getTransformationMethod() != null) {
                edit.setTransformationMethod(null);
                view.setImageResource(R.drawable.ic_eye_hide_grey_24px);
            } else {
                edit.setTransformationMethod(new PasswordTransformationMethod());
                view.setImageResource(R.drawable.ic_eye_show_grey);
            }
            edit.setSelection(edit.getText().length());
        });
    }

    /*
    * check admin setting fragment
    * */
    @BindingAdapter({ "bind:profile", "bind:type" })
    public static void onCheckedAdmin(AppCompatCheckBox view, ShopProfile profile, UserType type) {
        switch (type) {
            case USER1:
                if (profile.getUserAdmin1() == null) {
                    view.setChecked(false);
                } else {
                    view.setChecked(profile.getUserAdmin1().equals(DATA_ADMIN));
                }
                break;
            case USER2:
                if (profile.getUserAdmin2() == null) {
                    view.setChecked(false);
                } else {
                    view.setChecked(profile.getUserAdmin2().equals(DATA_ADMIN));
                }
                break;
            default:
                break;
        }
        view.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch (type) {
                case USER1:
                    profile.setUserAdmin1(isChecked ? DATA_ADMIN : null);
                    break;
                case USER2:
                    profile.setUserAdmin2(isChecked ? DATA_ADMIN : null);
                    break;
                default:
                    break;
            }
        });
    }

    /*
    * check user another user2
    * */

    @BindingAdapter({ "bind:checkUser", "bind:viewModel" })
    public static void checkUser(MaterialEditText view, MaterialEditText user,
            @NonNull final SettingViewModel viewModel) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = view.getText().toString().trim();
                String text2 = user.getText().toString().trim();
                if (text.equals(text2) && !text2.isEmpty()) {
                    view.setError(view.getContext().getString(R.string.msg_existing));
                    viewModel.setUserTrueFailed();
                }
            }
        });
    }
    /*
    * HistoryFragment
    * */
    /*
    * viewpager
    * */

    @BindingAdapter({ "bind:adapterViewPager", "bind:position" })
    public static void setUpViewPager(ViewPager view, FragmentPagerAdapter adapter, int position) {
        view.setAdapter(adapter);
        view.setCurrentItem(position);
    }

    /*
    * view pager of tab layout
     */
    @BindingAdapter({ "bind:viewPagerTabLayout" })
    public static void setUpTabLayout(TabLayout view, ViewPager viewPager) {
        view.setupWithViewPager(viewPager);
    }
    /*
    * bind change icon of Floating button Main shop
    * */

    @BindingAdapter({ "tools:iconDone" })
    public static void onChangeIcon(FloatingActionButton view, boolean isDone) {
        int icon = isDone ? R.drawable.ic_done_white_24px : R.drawable.ic_add_white_24dp;
        view.setImageResource(icon);
    }

    /*
    * bind on adapter pickImage
    * */
    @BindingAdapter({ "bind:onChecked", "bind:viewModel" })
    public static void onChecked(AppCompatImageView view, ImagePickerItem item,
            ImagePickerViewModel viewModel) {
        view.setSelected(item.isChecked());
        view.setOnClickListener(v -> {
            view.setSelected(!view.isSelected());
            item.setChecked(view.isSelected());
            viewModel.updateNumberImage();
        });
    }

    /*
    * Bind QRCode Of CouponCreation
    * */
    @BindingAdapter({ "bind:generate" })
    public static void generateQRCode(AppCompatImageView view, String content) {
        view.setImageBitmap(QRCode.from(content).bitmap());
    }

    /*
    * Bind spinner
    *
    * */
    @BindingAdapter(value = {
            "bind:selectedValue", "bind:selectedValueChanged"
    }, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner view, int newSelectedValue,
            final InverseBindingListener newTextAttrChanged) {
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != -1) {
            view.setSelection(newSelectedValue, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueChanged")
    public static int spinnerSelected(AppCompatSpinner view) {
        return view.getSelectedItemPosition();
    }

    /*
    * bind date time of create notification
    * */

    @BindingAdapter({ "bind:bindText" })
    public static void bindTime(AppCompatTextView view, long time) {
        view.setText(ActivityUtil.timeToString(time));
    }

    @BindingAdapter({ "bind:bindDayLeft" })
    public static void bindDayLeft(AppCompatTextView view, long time) {
        view.setText(ActivityUtil.dayLeft(time));
    }

    /*
    * bind  bind:attackToHeader ItemCouponOfShop
    * */
    @BindingAdapter({ "bind:adapterHeader", "bind:header" })
    public static void attackToHeader(RecyclerView view, RecyclerView.Adapter adapter,
            RecyclerViewHeader header) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(adapter);
        header.attachTo(view);
    }

    /*
    * bind drawerLayout
    * */
    @BindingAdapter({ "bind:drawerLayout", "bind:activity" })
    public static void drawerLayout(DrawerLayout view, Toolbar toolbar,
            AppCompatActivity activity) {
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(activity, view, toolbar, R.string.action_open,
                        R.string.action_close);
        view.setDrawerListener(toggle);
        toggle.syncState();
    }

    @BindingAdapter({ "bind:viewModel", "bind:drawerLayout", "bind:profile" })
    public static void eventNavigationCustomer(NavigationView view, MainViewModel viewModel,
            DrawerLayout drawerLayout, LCProfile profile) {
        Log.d("eventNavigationCustomer", "image = " + "pads");
        for (int i = 0; i < view.getHeaderCount(); i++) {
            view.removeHeaderView(view.getHeaderView(i));
        }
        NavHeaderMainBinding bindHeader =
                NavHeaderMainBinding.inflate(LayoutInflater.from(view.getContext()));
        bindHeader.setProfile(profile);
        bindHeader.executePendingBindings();
        view.addHeaderView(bindHeader.getRoot());
        view.setNavigationItemSelectedListener(item -> {
            viewModel.onNavigationListener(item.getItemId());
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    /*
    * bind time item coupon of shop in item_coupon_of_shop
    * */
    @BindingAdapter({ "bind:timeCouponOfShop", "bind:duration" })
    public static void bindTime(AppCompatTextView view, long dateCreated, int duration) {
        view.setText(ActivityUtil.convertDateCouponOfShop(dateCreated, duration));
    }

    /*
    * bind date left item coupon of shop in item_coupon_of_shop
    * */
    @BindingAdapter({ "bind:timeDateLeft", "bind:duration" })
    public static void bindDateLeft(AppCompatTextView view, long dateCreated, int duration) {
        view.setText(ActivityUtil.dayLeft(dateCreated, duration));
    }
}
