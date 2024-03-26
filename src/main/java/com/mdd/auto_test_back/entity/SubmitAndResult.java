package com.mdd.auto_test_back.entity;

import java.util.List;

public class SubmitAndResult {
    private List<Result> resultList;
    private Submit submit;

    public SubmitAndResult(List<Result> resultList, Submit submit) {
        this.resultList = resultList;
        this.submit = submit;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public Submit getSubmit() {
        return submit;
    }

    public void setSubmit(Submit submit) {
        this.submit = submit;
    }
}
