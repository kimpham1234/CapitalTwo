package capitaltwo;

import javax.persistence.*; // TODO change
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionItemId implements Serializable {
    
    @Column(name="transaction_id")
    private Long transactionId;
    
    @Column(name="item_id")
    private Long itemId;

    protected TransactionItemId() {}

    public TransactionItemId(Long transactionId,
                             Long itemId) {
        this.transactionId = transactionId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TransactionItemId obj = (TransactionItemId)o;
        return Objects.equals(this.transactionId, obj.transactionId) &&
               Objects.equals(this.itemId, obj.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, itemId);
    }

}
