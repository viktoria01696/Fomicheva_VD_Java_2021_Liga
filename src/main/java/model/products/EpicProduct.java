package model.products;

import lombok.*;
import javax.validation.constraints.Min;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class EpicProduct implements Product {

    @EqualsAndHashCode.Include
    private final String ID;
    private final String productName;
    @NonNull
    @Min(0)
    private Double price;
    @NonNull
    @Min(0)
    private Integer amountInStore;

    @Override
    public boolean checkAmountForBuying(Integer amount){
        return this.amountInStore>=amount;
    }

    @Override
    public boolean reduceAmountInStore(Integer amount) {
        if (checkAmountForBuying(amount)){
            this.amountInStore -=amount;
            return true;
        }
        else {
            return false;
        }
    }
}


