package com.example.aisummarizer.aisummarizer.donate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;
import com.example.aisummarizer.aisummarizer.utils.Utility;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.view.CardMultilineWidget;

public class PaymentActivity extends SuperCompatActivity {

    TextInputLayout holderNameIL;
    TextInputEditText holderName;
    CardInputWidget mCardInputWidget;
    CardMultilineWidget add_source_card_entry_widget;

    /*
     * Change this to your publishable key.
     *
     * You can get your key here: https://dashboard.stripe.com/account/apikeys
     */
    private static final String PUBLISHABLE_KEY =
            "pk_test_Vrd2blZ87ljzxBznvpLFnVon";//put your publishable key here

    //private String userSecretKey = "";

    static final int PURCHASE_REQUEST = 37;

    private static final String EXTRA_PRICE_PAID = "EXTRA_PRICE_PAID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        holderNameIL = findViewById(R.id.holderNameIL);
        holderName = findViewById(R.id.holderName);

        mCardInputWidget = findViewById(R.id.card_input_widget);
        add_source_card_entry_widget = findViewById(R.id.add_source_card_entry_widget);

        PaymentConfiguration.init(PUBLISHABLE_KEY);

        setupCustomerSession();
    }

    public void donate(View view) {
        //finish();

        //Card card = new Card(cardNumber, cardExpMonth, cardExpYear, cardCVC);

        Card card = add_source_card_entry_widget.getCard();
        Utility myUtility = new Utility(PaymentActivity.this);
        try {
            if (card != null) {
                card.setName("" + holderName.getText().toString());
            }
            boolean validation = card != null && card.validateCard();

            if (!validation) {
                if (card == null) {
                    myUtility.setAlertMessage("Error", "The card details that you entered are invalid.");
                } else if (!card.validateNumber()) {
                    myUtility.setAlertMessage("Error", "The card number that you entered is invalid.");
                } else if (!card.validateExpiryDate()) {
                    myUtility.setAlertMessage("Error", "The expiration date that you entered is invalid.");
                } else if (!card.validateCVC()) {
                    myUtility.setAlertMessage("Error", "The CVC code that you entered is invalid.");
                }
            } else {
                Stripe stripe = new Stripe(PaymentActivity.this, PUBLISHABLE_KEY);
                stripe.createToken(
                        card,
                        new TokenCallback() {
                            public void onSuccess(Token token) {
                                // Send token to your own web service
                                myUtility.setAlertMessage("Token", "" + token.getId());
                            }

                            public void onError(Exception error) {
                                // Show localized error message
                                Toast.makeText(PaymentActivity.this,
                                        error.getLocalizedMessage(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                );
            }

        } catch (Exception e) {
            myUtility.setAlertMessage("Error", "" + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == PURCHASE_REQUEST
                && resultCode == RESULT_OK
                && data.getExtras() != null) {
            long price = data.getExtras().getLong(EXTRA_PRICE_PAID, -1L);
            if (price != -1L) {
                //displayPurchase(price);
            }
        }*/
    }

    private void setupCustomerSession() {
        // CustomerSession only needs to be initialized once per app.
        /*CustomerSession.initCustomerSession(
                new SampleStoreEphemeralKeyProvider(
                        new SampleStoreEphemeralKeyProvider.ProgressListener() {
                            @Override
                            public void onStringResponse(String string) {
                                if (string.startsWith("Error: ")) {
                                    new AlertDialog.Builder(PaymentActivity.this).setMessage(string).show();
                                }
                            }
                        }));*/
    }
}
