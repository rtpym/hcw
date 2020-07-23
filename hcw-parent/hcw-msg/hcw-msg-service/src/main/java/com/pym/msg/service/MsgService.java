package com.pym.msg.service;

import com.pym.msg.model.Content;
import com.pym.msg.model.MsgModel;

public interface MsgService<T extends Content> {
   boolean sendMsg(MsgModel<T> msgModel);
}
