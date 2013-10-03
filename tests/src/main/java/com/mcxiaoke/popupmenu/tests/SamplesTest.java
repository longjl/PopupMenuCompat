package com.mcxiaoke.popupmenu.tests;

import com.mcxiaoke.popupmenu.samples.Samples;
import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;

/**
 * User: mcxiaoke
 * Date: 13-10-1
 * Time: 上午11:25
 */
public class SamplesTest extends ActivityInstrumentationTestCase2<Samples> {
    private Solo solo;

    public SamplesTest() {
        super(Samples.class);
    }

    public void testClass() {
        assertEquals(Samples.class, getActivity().getClass());
    }

    public void testa() {

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
