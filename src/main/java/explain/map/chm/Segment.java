package explain.map.chm;

import explain.concurrent.aqs.rttl.ReentrantLock;

import java.io.Serializable;

/**
 * Stripped-down version of helper class used in previous version,
 * declared for the sake of serialization compatibility
 */
public class Segment<K,V> extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
        final float loadFactor;
        Segment(float lf) { this.loadFactor = lf; }
    }
