package diplom;

import java.util.List;

public class Ingredients {
    private boolean success;
    private List<IngredientsData> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<IngredientsData> getData() {
        return data;
    }

    public void setData(List<IngredientsData> data) {
        this.data = data;
    }
}
