package hospital;

import java.io.IOException;

public abstract class Medical implements Hospital {

    public String beforeTreatment() {
        return "ready";
    }

    public abstract boolean duringTreatment() throws IOException;
}
