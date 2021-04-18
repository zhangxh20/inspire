package com.github.inspire.manager;

import com.github.inspire.entity.Rule;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RuleEngine {

    public void execute(Map<String, Object> content, List<Rule> rules, ExecuteContext context) {
        for (Rule rule : rules) {
            Boolean result = execute(rule.getExpression(), content);
            metrics(result, context, content);
        }
    }

    private Boolean execute(String expression, Map<String, Object> content) {
        Expression compile = AviatorEvaluator.compile(expression);
        return (Boolean) compile.execute(content);
    }

    private void metrics(boolean result, ExecuteContext context, Map<String, Object> content) {
        context.getTotalCount().increment();
        if (result) {
            context.getSuccessCount().increment();
        } else {
            context.getFailCount().increment();
            List<Map<String, Object>> failList = context.getFailList();
            if (failList != null) {
                synchronized (failList) {
                    failList.add(content);
                }
            }
        }
    }
}
