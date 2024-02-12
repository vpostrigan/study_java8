package modern_java_recipes.chapter5_optional_type;

/**
 * Требуется создать сообщение, но только если задан уровень протоколирования,
 * при котором оно должно быть видно.
 */
public class g_LoggerWithSupplier {
    // private Logger logger = Logger.getLogger(this.getClass().getName());
    // private List<String> data = new ArrayList<>();

    public static void main(String[] args) {
        // Техника замены аргумента объектом Supplier соответствующего типа называется отложенным выполнением

        // logger.info("Данные " + data.toString());
        // logger.info(() -> "Данные " + data.toString());
    }

}
