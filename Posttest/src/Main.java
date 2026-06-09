// INTERFACE PAYMENT
interface Payable {
    void pay(double amount);
}

interface Refundable {
    void refund(double amount);
}


// INTERFACE NOTIFIER
interface EmailNotifier {
    void sendEmail(String message);
}

interface SMSNotifier {
    void sendSMS(String message);
}

interface WhatsAppNotifier {
    void sendWhatsApp(String message);
}


// PAYMENT METHOD
class CreditCard implements Payable, Refundable {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran Credit Card : Rp" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refund Credit Card : Rp" + amount);
    }
}

class OVO implements Payable, Refundable {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran OVO : Rp" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refund OVO : Rp" + amount);
    }
}

class GiftVoucher implements Payable {

    @Override
    public void pay(double amount) {
        System.out.println("Pembayaran Gift Voucher : Rp" + amount);
    }
}


// NOTIFICATION SYSTEM
class EmailSystem implements EmailNotifier {

    @Override
    public void sendEmail(String message) {
        System.out.println("EMAIL : " + message);
    }
}

class SMSSystem implements SMSNotifier {

    @Override
    public void sendSMS(String message) {
        System.out.println("SMS : " + message);
    }
}

class WhatsAppSystem implements WhatsAppNotifier {

    @Override
    public void sendWhatsApp(String message) {
        System.out.println("WHATSAPP : " + message);
    }
}


// PAYMENT SERVICE
class PaymentService {

    private Payable paymentMethod;

    public PaymentService(Payable paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {
        paymentMethod.pay(amount);
    }
}


// NOTIFICATION SERVICE
class NotificationService {

    private EmailNotifier notifier;

    public NotificationService(EmailNotifier notifier) {
        this.notifier = notifier;
    }

    public void sendNotification(String message) {
        notifier.sendEmail(message);
    }
}


// ORDER SERVICE
class OrderService {

    private PaymentService paymentService;
    private NotificationService notificationService;

    public OrderService(
            PaymentService paymentService,
            NotificationService notificationService) {

        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public void createOrder(double amount) {

        System.out.println("Pesanan berhasil dibuat");

        paymentService.processPayment(amount);

        notificationService.sendNotification(
                "Pembayaran berhasil"
        );
    }
}

// MAIN
public class Main {

    public static void main(String[] args) {

        System.out.println("=== SIMULASI ORDER ===");

        Payable payment = new OVO();

        EmailNotifier notifier = new EmailSystem();

        PaymentService paymentService =
                new PaymentService(payment);

        NotificationService notificationService =
                new NotificationService(notifier);

        OrderService orderService =
                new OrderService(
                        paymentService,
                        notificationService
                );

        orderService.createOrder(50000);
    }
}