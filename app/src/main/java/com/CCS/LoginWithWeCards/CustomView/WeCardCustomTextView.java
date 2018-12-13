package com.CCS.LoginWithWeCards.CustomView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.CCS.LoginWithWeCards.R;
import com.CCS.LoginWithWeCards.Utils.TypefaceUtils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.ColorUtils;

/**
 * Created by mauliksantoki on 21/8/17.
 */

public class WeCardCustomTextView extends AppCompatTextView {


    public WeCardCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.WeCardCustomTextView,
                0, 0);

        int typeface = a.getInt(R.styleable.WeCardCustomTextView_weTvTypeface, 0);
        int color = a.getColor(R.styleable.WeCardCustomTextView_we_ctvTxtColor, 0);
        int selectedColor = a.getColor(R.styleable.WeCardCustomTextView_we_ctvSelectedTxtColor, 0);

        setTypeface(typeface);

        if (selectedColor == 0) {
            selectedColor = color;
        }

        setCustomTextColor(color, selectedColor);
        setClickable(isClickable());
    }

    private void setTypeface(int typeface) {
        if (typeface == com.CCS.LoginWithWeCards.Utils.TypefaceUtils.INT_CODE_BOLD_FONT) {
            setTypeface(com.CCS.LoginWithWeCards.Utils.TypefaceUtils.getInstance().getBoldTypeface(getContext()));
        } else {
            setTypeface(TypefaceUtils.getInstance().getRegularTypeface(getContext()));
        }
    }

    private void setCustomTextColor(int color, int selectedColor) {
        setTextColor(getPressedColorSelector(color, isClickable() ? getTransparentColor(color) : color, selectedColor));
    }

    private static ColorStateList getPressedColorSelector(int normalColor, int pressedColor, int selectedTextColor) {
        // sagar : 5/12/18 Orders are so important here.
        /*https://stackoverflow.com/questions/15543186/how-do-i-create-colorstatelist-programmatically*/
        // sagar : 5/12/18 Check comment of ToolMakerSteve there under accepted answer of Caner
        // sagar : 5/12/18 Consider color state list as if-else block only
        return new ColorStateList(
                new int[][]
                        {
                                new int[]{android.R.attr.state_pressed},
                                new int[]{android.R.attr.state_focused},
                                new int[]{android.R.attr.state_activated},
                                new int[]{android.R.attr.state_selected},
                                new int[]{android.R.attr.state_enabled},
                                new int[]{}
                        },
                new int[]
                        {
                                pressedColor,
                                pressedColor,
                                selectedTextColor,
                                selectedTextColor,
                                normalColor,
                                normalColor
                        }
        );
    }

    private int getTransparentColor(int color) {
        return ColorUtils.setAlphaComponent(color, 128);
    }

    public WeCardCustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }
}
