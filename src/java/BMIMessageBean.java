
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * MDB receives a BmiRecord and stores it into database
 * @author middleware
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/bmi")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BMIMessageBean implements MessageListener {

    @PersistenceContext(unitName = "Exercise11PU")
    private EntityManager em;
    
    
    
    public BMIMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            BmiRecord record = (BmiRecord) message.getBody(BmiRecord.class);
            persist(record);
            
        } catch (JMSException ex) {
            Logger.getLogger(BMIMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void persist(BmiRecord object) {
        em.persist(object);
        em.flush();
    }
    
}
