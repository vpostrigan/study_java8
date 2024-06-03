package image;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/*
import utils.url.PostRequest;
import utils.url.PostRequestUploadObject;
import utils.url.RequestObject;
import utils.xml.xslt.Xslt;
*/

public class OnLineNewocr {/*
    private static final Xslt XSLT = new Xslt("//*[@id='ocr-result']");

    private OnLineNewocr() {
    }

    public static RequestObject getRequestObject(byte[] data) throws IOException {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("l", "eng");
        parameters.put("r", "0");
        parameters.put("psm", "6");
        // parameters.put("u", "1a686cc400288aa514e90a489cc0bf06");
        // parameters.put("x1", "2");
        // parameters.put("x2", "693");
        // parameters.put("y1", "0");
        // parameters.put("y2", "544");
        parameters.put("ocr", "1");

        PostRequestUploadObject uploadObject = new PostRequestUploadObject("userfile", "FixedGraph_"
                + System.currentTimeMillis() + ".jpeg", "image/jpeg", data, parameters);

        PostRequest postRequest = new PostRequest(null, true);
        postRequest.setPostRequestUploadObject(uploadObject);

        RequestObject requestObject = new RequestObject("http://www.newocr.com/", postRequest);
        requestObject.setXslt(XSLT);

        return requestObject;
    }
*/
}
