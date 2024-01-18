/*
 * Copyright 2022 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.flowui.xml.layout.loader.html;

import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.xml.layout.support.DataLoaderSupport;

public class ImageLoader extends AbstractHtmlContainerLoader<JmixImage<?>> {

    protected DataLoaderSupport dataLoaderSupport;

    @Override
    protected JmixImage<?> createComponent() {
        return factory.create(JmixImage.class);
    }

    @Override
    public void loadComponent() {
        super.loadComponent();

        loadString(element, "resource", resultComponent::setSrc);
        loadResourceString(element, "alternateText", context.getMessageGroup(), resultComponent::setAlt);

        componentLoader().loadAriaLabel(resultComponent, element);
        componentLoader().loadClickNotifierAttributes(resultComponent, element);

        getDataLoaderSupport().loadData(resultComponent, element);
    }

    protected DataLoaderSupport getDataLoaderSupport() {
        if (dataLoaderSupport == null) {
            dataLoaderSupport = applicationContext.getBean(DataLoaderSupport.class, context);
        }
        return dataLoaderSupport;
    }
}
