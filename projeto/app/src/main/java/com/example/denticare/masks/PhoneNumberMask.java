package com.example.denticare.masks;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneNumberMask{
    private static final String PHONE_MASK = "(##) #####-####";

    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isFormatting;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) {
                    return;
                }

                String str = s.toString().replaceAll("[^\\d]", "");
                if (str.equals(old) || str.isEmpty()) {
                    return;
                }

                isFormatting = true;
                StringBuilder formatted = new StringBuilder();
                int index = 0;

                for (char c : PHONE_MASK.toCharArray()) {
                    if (c != '#') {
                        formatted.append(c);
                        continue;
                    }

                    try {
                        formatted.append(str.charAt(index));
                        index++;
                    } catch (Exception e) {
                        break;
                    }
                }

                editText.removeTextChangedListener(this);
                editText.setText(formatted);
                editText.setSelection(formatted.length());
                editText.addTextChangedListener(this);

                isFormatting = false;
                old = str;
            }
        };
    }
}
