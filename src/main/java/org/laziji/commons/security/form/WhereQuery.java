package org.laziji.commons.security.form;

import java.util.Map;
import java.util.Set;

public class WhereQuery extends BaseForm {

    private Map<String, Object> equal;
    private Map<String, Object> notEqual;
    private Map<String, Object> greaterOrEqual;
    private Map<String, Object> lesserOrEqual;
    private Map<String, Object> greater;
    private Map<String, Object> lesser;
    private Map<String, Object> like;
    private Map<String, Object> likeLeft;
    private Map<String, Object> likeRight;
    private Map<String, Object> notLike;
    private Map<String, Set<Object>> in;
    private Map<String, Set<Object>> notIn;
    private Set<String> isNull;
    private Set<String> isNotNull;
    private Set<String> orderByAsc;
    private Set<String> orderByDesc;
    private WhereQuery or;

    public static String convertName(String name) {
        char[] chars = name.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : chars) {
            if (ch >= 'A' && ch <= 'Z') {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('_');
                }
                stringBuilder.append((char) (ch - 'A' + 'a'));
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public Map<String, Object> getEqual() {
        return equal;
    }

    public void setEqual(Map<String, Object> equal) {
        this.equal = equal;
    }

    public Map<String, Object> getNotEqual() {
        return notEqual;
    }

    public void setNotEqual(Map<String, Object> notEqual) {
        this.notEqual = notEqual;
    }

    public Map<String, Object> getGreaterOrEqual() {
        return greaterOrEqual;
    }

    public void setGreaterOrEqual(Map<String, Object> greaterOrEqual) {
        this.greaterOrEqual = greaterOrEqual;
    }

    public Map<String, Object> getLesserOrEqual() {
        return lesserOrEqual;
    }

    public void setLesserOrEqual(Map<String, Object> lesserOrEqual) {
        this.lesserOrEqual = lesserOrEqual;
    }

    public Map<String, Object> getGreater() {
        return greater;
    }

    public void setGreater(Map<String, Object> greater) {
        this.greater = greater;
    }

    public Map<String, Object> getLesser() {
        return lesser;
    }

    public void setLesser(Map<String, Object> lesser) {
        this.lesser = lesser;
    }

    public Map<String, Object> getLike() {
        return like;
    }

    public void setLike(Map<String, Object> like) {
        this.like = like;
    }

    public Map<String, Object> getLikeLeft() {
        return likeLeft;
    }

    public void setLikeLeft(Map<String, Object> likeLeft) {
        this.likeLeft = likeLeft;
    }

    public Map<String, Object> getLikeRight() {
        return likeRight;
    }

    public void setLikeRight(Map<String, Object> likeRight) {
        this.likeRight = likeRight;
    }

    public Map<String, Object> getNotLike() {
        return notLike;
    }

    public void setNotLike(Map<String, Object> notLike) {
        this.notLike = notLike;
    }

    public Map<String, Set<Object>> getIn() {
        return in;
    }

    public void setIn(Map<String, Set<Object>> in) {
        this.in = in;
    }

    public Map<String, Set<Object>> getNotIn() {
        return notIn;
    }

    public void setNotIn(Map<String, Set<Object>> notIn) {
        this.notIn = notIn;
    }

    public Set<String> getIsNull() {
        return isNull;
    }

    public void setIsNull(Set<String> isNull) {
        this.isNull = isNull;
    }

    public Set<String> getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(Set<String> isNotNull) {
        this.isNotNull = isNotNull;
    }

    public Set<String> getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Set<String> orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public Set<String> getOrderByDesc() {
        return orderByDesc;
    }

    public void setOrderByDesc(Set<String> orderByDesc) {
        this.orderByDesc = orderByDesc;
    }

    public WhereQuery getOr() {
        return or;
    }

    public void setOr(WhereQuery or) {
        this.or = or;
    }
}