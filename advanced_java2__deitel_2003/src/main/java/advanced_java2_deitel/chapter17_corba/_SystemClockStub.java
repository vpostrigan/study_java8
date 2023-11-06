package advanced_java2_deitel.chapter17_corba;

/**
 * com/deitel/advjhtp1/idl/clock/_SystemClockStub.java
 * Generated by the IDL-to-Java compiler (portable), version "3.0"
 * from systemclock.idl
 * Sunday, July 1, 2001 10:36:53 PM PDT
 */


// The definition of the CORBA-enabled service
public class _SystemClockStub extends org.omg.CORBA.portable.ObjectImpl implements SystemClock {
    // Constructors
    // NOTE:  If the default constructor is used, the
    //        object is useless until _set_delegate (...)
    //        is called.
    public _SystemClockStub() {
        super();
    }

    public _SystemClockStub(org.omg.CORBA.portable.Delegate delegate) {
        super();
        _set_delegate(delegate);
    }

    public long currentTimeMillis() {
        org.omg.CORBA.portable.InputStream _in = null;
        try {
            org.omg.CORBA.portable.OutputStream _out = _request("currentTimeMillis", true);
            _in = _invoke(_out);
            long __result = _in.read_longlong();
            return __result;
        } catch (org.omg.CORBA.portable.ApplicationException _ex) {
            _in = _ex.getInputStream();
            String _id = _ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        } catch (org.omg.CORBA.portable.RemarshalException _rm) {
            return currentTimeMillis();
        } finally {
            _releaseReply(_in);
        }
    } // currentTimeMillis

    // Type-specific CORBA::Object operations
    private static String[] __ids = {
            "IDL:clock/SystemClock:1.0"};

    public String[] _ids() {
        return (String[]) __ids.clone();
    }

    private void readObject(java.io.ObjectInputStream s) {
        try {
            String str = s.readUTF();
            org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init().string_to_object(str);
            org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate();
            _set_delegate(delegate);
        } catch (java.io.IOException e) {
        }
    }

    private void writeObject(java.io.ObjectOutputStream s) {
        try {
            String str = org.omg.CORBA.ORB.init().object_to_string(this);
            s.writeUTF(str);
        } catch (java.io.IOException e) {
        }
    }
}