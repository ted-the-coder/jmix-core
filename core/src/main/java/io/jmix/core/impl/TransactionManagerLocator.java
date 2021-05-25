/*
 * Copyright 2021 Haulmont.
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

package io.jmix.core.impl;

import io.jmix.core.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component("core_TransactionManagerLocator")
public class TransactionManagerLocator {

    @Autowired
    protected ApplicationContext applicationContext;

    public PlatformTransactionManager getTransactionManager(String storeName) {
        if (Stores.isMain(storeName)) {
            return applicationContext.getBean("transactionManager", PlatformTransactionManager.class);
        }
        return applicationContext.getBean(storeName + "TransactionManager", PlatformTransactionManager.class);
    }
}
