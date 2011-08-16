/* Windward Server - Copyright (C) 2011  Tony Sweets tony.sweets at gmail dot com */
/*
 *  This file is part of Windward Server.
 *
 *  Windward Server is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Windward Server is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Windward Server.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.beer30.winsvr;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: tsweets
 * Date: 8/8/11
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement
public class WindwardDocMsg {
    // To create a Windward Doc we need a Template
    // and a Data Structure of the dynamic data to fill in

    private String templateName;
    private String xmlData;
    private byte[] template;

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] template) {
        this.template = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }
}
