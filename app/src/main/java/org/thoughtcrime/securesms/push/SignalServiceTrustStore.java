package org.thoughtcrime.securesms.push;

import android.content.Context;

import org.thoughtcrime.securesms.BuildConfig;
import org.thoughtcrime.securesms.R;
import org.signal.network.config.TrustStore;

import java.io.InputStream;

public class SignalServiceTrustStore implements TrustStore {

  private final Context context;

  public SignalServiceTrustStore(Context context) {
    this.context = context.getApplicationContext();
  }

  @Override
  public InputStream getKeyStoreInputStream() {
    // Self-hosted servers use public certificates (Let's Encrypt ISRG Root X1/X2) instead of Signal's private CA.
    return context.getResources().openRawResource(BuildConfig.SELF_HOSTED ? R.raw.selfhost : R.raw.whisper);
  }

  @Override
  public String getKeyStorePassword() {
    return BuildConfig.SELF_HOSTED ? "selfhost" : "whisper";
  }
}
