package com.report.html;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HtmlReportFomatter extends Formatter {
    private int i = 0;
    private long setStartTime;
    private long setEndTime;
    private final String HTML_HEADER = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><meta http-equiv=\"Content-Type\"content=\"text/html;charset=utf-8\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\"><META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\"><META HTTP-EQUIV=\"PRAGMA\" CONTENT=\"NO-CACHE\"><link rel=\"stylesheet\" href=\"demo_report_style.css\"/><html><head><title>测试报告</title></head><body><div class=\"page_title\"><center><h1>测试报告</h1></center></div><div class=\"statistics\"><table id=\"statistics_table\" class=\"sortable\" align=\"center\" border=\"0\"  style=\"width:100%;\"><tr><th><b>序号</b></th><th><b>用例描述</b></th><th><b>期待结果</b></th><th><b>实际结果</b></th><th><b>执行时间</b></th><th><b>状态</b></th></tr>";

    public HtmlReportFomatter() {
    }

    private int recordStep() {
        ++this.i;
        return this.i;
    }

    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<div class=\"statistics\">");
        buf.append("<tr>");
        buf.append("<td>");
        buf.append(this.recordStep());
        buf.append("</td>");
        buf.append("<td>");
        buf.append(this.formatMessage(rec));
        buf.append('\n');
        buf.append("</td>");
        buf.append("<td>");
        buf.append(RecordStore.expected);
        buf.append("</td>");
        buf.append("<td>");
        buf.append(RecordStore.actual);
        buf.append("</td>");
        buf.append("<td>");
        buf.append(HtmlUtil.getCalcDate(rec.getMillis()));
        buf.append("</td>");
        buf.append("<td>");
        if (!RecordStore.result.matches("Pass") && !RecordStore.result.matches("PASS")) {
            if (!RecordStore.result.matches("Fail") && !RecordStore.result.matches("FAIL")) {
                buf.append("<b>");
                buf.append("");
                buf.append("</b>");
            } else {
                ++RecordStore.p_fail;
                buf.append("<b>");
                buf.append("<font color=Red>");
                buf.append(RecordStore.result);
                buf.append("</font>");
                buf.append("</b>");
            }
        } else {
            ++RecordStore.p_pass;
            buf.append("<b>");
            buf.append("<font color=Green>");
            buf.append(RecordStore.result);
            buf.append("</font>");
            buf.append("</b>");
        }

        buf.append("</td>");
        buf.append("</tr>");
        buf.append("</div>\n");

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }

        return buf.toString();
    }

    public String getHead(Handler h) {
        this.setStartTime = System.currentTimeMillis();
        System.out.println("starttime: " + this.setStartTime);
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\"><META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\"><META HTTP-EQUIV=\"PRAGMA\" CONTENT=\"NO-CACHE\"><link rel=\"stylesheet\" href=\"demo_report_style.css\"/><html><head><title>测试报告</title></head><body><div class=\"page_title\"><center><h1>测试报告</h1></center></div><div class=\"statistics\"><table id=\"statistics_table\" class=\"sortable\" align=\"center\" border=\"0\"  style=\"width:100%;\"><tr><th><b>序号</b></th><th><b>用例描述</b></th><th><b>期待结果</b></th><th><b>实际结果</b></th><th><b>执行时间</b></th><th><b>状态</b></th></tr>";
    }

    public String getTail(Handler h) {
        this.setEndTime = System.currentTimeMillis();
        System.out.println("endtime: " + this.setEndTime);
        int p_total = RecordStore.p_pass + RecordStore.p_fail;
        String HTML_Tail;
        if (p_total > 0) {
            if (RecordStore.p_fail > 0) {
                HTML_Tail = "</table></PRE><br>&nbsp;开始时间   ：" + HtmlUtil.getCalcDate(this.setStartTime) + "<br>&nbsp;结束时间      ：" + HtmlUtil.getCalcDate(this.setEndTime) + "<br>&nbsp;运行时间      ：" + HtmlUtil.getDeltaTime(this.setEndTime, this.setStartTime) + "<br>&nbsp;执行用例      ：" + p_total + "<br>&nbsp;用例成功         ：" + RecordStore.p_pass + "<br>&nbsp;<font color=Red>用例失败      ：" + RecordStore.p_fail + "</font>" + "<br>&nbsp;成功率(%) ：" + HtmlUtil.getPercnet((double)RecordStore.p_pass, (double)p_total) + "<br>&nbsp;<font color=Red>失败率(%) ：" + HtmlUtil.getPercnet((double)RecordStore.p_fail, (double)p_total) + "</font>" + "<br><br>" + "</BODY></HTML>";
            } else {
                HTML_Tail = "</table></PRE><br>&nbsp;开始时间   ：" + HtmlUtil.getCalcDate(this.setStartTime) + "<br>&nbsp;结束时间   ：" + HtmlUtil.getCalcDate(this.setEndTime) + "<br>&nbsp;运行时间   ：" + HtmlUtil.getDeltaTime(this.setEndTime, this.setStartTime) + "<br>&nbsp;执行用例      ：" + p_total + "<br>&nbsp;用例成功      ：" + RecordStore.p_pass + "<br>&nbsp;用例失败      ：" + RecordStore.p_fail + "<br>&nbsp;成功率(%) ：" + HtmlUtil.getPercnet((double)RecordStore.p_pass, (double)p_total) + "<br>&nbsp;失败率(%) ：" + HtmlUtil.getPercnet((double)RecordStore.p_fail, (double)p_total) + "<br><br>" + "</BODY></HTML>";
            }
        } else {
            HTML_Tail = "</table></PRE><br>&nbsp;用例执行异常！<br><br></BODY></HTML>";
        }

        return HTML_Tail;
    }
}
