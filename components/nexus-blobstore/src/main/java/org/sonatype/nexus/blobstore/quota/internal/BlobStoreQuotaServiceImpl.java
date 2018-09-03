/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.blobstore.quota.internal;

import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.blobstore.api.BlobStore;
import org.sonatype.nexus.blobstore.api.BlobStoreConfiguration;
import org.sonatype.nexus.blobstore.quota.BlobStoreQuota;
import org.sonatype.nexus.blobstore.quota.BlobStoreQuotaResult;
import org.sonatype.nexus.blobstore.quota.BlobStoreQuotaService;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.sonatype.nexus.blobstore.quota.BlobStoreQuotaSupport.ROOT_KEY;
import static org.sonatype.nexus.blobstore.quota.BlobStoreQuotaSupport.TYPE_KEY;

/**
 * Default implementation of {@link BlobStoreQuotaService}
 *
 * @since 3.next
 */
@Named
@Singleton
public class BlobStoreQuotaServiceImpl
    extends ComponentSupport
    implements BlobStoreQuotaService
{
  private final Map<String, BlobStoreQuota> quotas;

  @Inject
  public BlobStoreQuotaServiceImpl(final Map<String, BlobStoreQuota> quotas) {
    this.quotas = checkNotNull(quotas);
  }

  @Nullable
  @Override
  public BlobStoreQuotaResult checkQuota(final BlobStore blobStore) {
    checkNotNull(blobStore);
    BlobStoreConfiguration config = blobStore.getBlobStoreConfiguration();
    String quotaType = config.attributes(ROOT_KEY).get(TYPE_KEY, String.class);
    log.debug("Checking blobstore {} with quota type {}", config.getName(), quotaType);
    if (quotaType == null) {
      return null;
    }

    BlobStoreQuota quota = quotas.get(quotaType);
    if (quota == null) {
      log.error("Unable to find corresponding quota type for key {}", quotaType);
      return null;
    }
    else {
      return quota.check(blobStore);
    }
  }
}
