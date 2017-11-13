package capitaltwo;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import java.util.Date;
import java.util.Objects;

@Entity
public class TransactionItem {

    protected TransactionItem() {}

    public TransactionItem(Transaction transaction,
                           Item item,
                           int quantity,
                           double unitPrice) {
        System.out.println("creating transaction " + transaction.getId() +
            " and item " + item.getId());
        this.transaction = transaction;
        this.item = item;
        this.id = new TransactionItemId(transaction.getId(), item.getId());
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @EmbeddedId
    private TransactionItemId id;

    @ManyToOne
    @MapsId("transactionId")
    private Transaction transaction;
    @ManyToOne
    @MapsId("itemId")
    private Item item;

    private int quantity;
    private double unitPrice;

    public Transaction getTransaction() { return this.transaction; }
    public Item getItem() { return this.item; }
    public int getQuantity() { return this.quantity; }
    public double getUnitPrice() { return this.unitPrice; }

    public void setTransaction(Transaction transaction) { this.transaction = transaction; }
    public void setItem(Item item) { this.item = item; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnitPrice(double price) { this.unitPrice = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TransactionItem obj = (TransactionItem)o;
        return Objects.equals(transaction, obj.transaction) &&
               Objects.equals(item, obj.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction, item);
    }

}
