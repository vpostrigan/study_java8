package image;

public class OCRTest {

    public static void main(String[] args) {
        for (;;) {
            byte[] bytes = IoUtil.getResource(HardcodedOcr.class, "FixedGraph.aspx.jpeg");
            long t = System.currentTimeMillis();
            String str = HardcodedOcr.extractText(bytes);
            System.out.println(str);
            System.out.println(System.currentTimeMillis() - t);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
