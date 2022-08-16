package com.barify.stepsdef;

import android.graphics.pdf.PdfDocument;
import com.barify.pages.PagesFactory;
import com.barify.pages.examplePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class exampleSteps {
    private examplePage exampleStepsPage;

    private exampleSteps() {
        exampleStepsPage = PagesFactory.getInstance().getExamplePage();
    }
}
