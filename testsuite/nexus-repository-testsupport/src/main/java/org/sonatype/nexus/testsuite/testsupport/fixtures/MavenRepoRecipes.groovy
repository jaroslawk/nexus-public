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
package org.sonatype.nexus.testsuite.testsupport.fixtures

import javax.annotation.Nonnull

import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.Repository
import org.sonatype.nexus.repository.config.Configuration
import org.sonatype.nexus.repository.maven.LayoutPolicy
import org.sonatype.nexus.repository.maven.VersionPolicy
import org.sonatype.nexus.repository.storage.WritePolicy

import groovy.transform.CompileStatic

/**
 * Factory for Maven {@link Repository} {@link Configuration}
 */
@CompileStatic
trait MavenRepoRecipes
    extends ConfigurationRecipes
{
  @Nonnull
  Repository createMavenHosted(final String name,
                               final VersionPolicy versionPolicy = VersionPolicy.RELEASE,
                               final WritePolicy writePolicy = WritePolicy.ALLOW_ONCE,
                               final LayoutPolicy layoutPolicy = LayoutPolicy.STRICT,
                               final String blobStoreName = BlobStoreManager.DEFAULT_BLOBSTORE_NAME)
  {
    Configuration configuration =
        createHosted(name, 'maven2-hosted', writePolicy, true, blobStoreName)
    configuration.attributes.maven = configureMaven(versionPolicy, layoutPolicy)
    createRepository(configuration)
  }

  @Nonnull
  Repository createMavenProxy(final String name,
                              final String remoteUrl,
                              final VersionPolicy versionPolicy = VersionPolicy.RELEASE,
                              final LayoutPolicy layoutPolicy = LayoutPolicy.STRICT)
  {
    Configuration configuration = createProxy(name, 'maven2-proxy', remoteUrl)
    configuration.attributes.maven = configureMaven(versionPolicy, layoutPolicy)
    createRepository(configuration)
  }

  @Nonnull
  Repository createMavenGroup(final String name,
                              final String... members)
  {
    Configuration configuration = createGroup(name, 'maven2-group', members)
    configuration.attributes.maven = configureMaven()
    createRepository(configuration)
  }

  private Map configureMaven(final VersionPolicy versionPolicy = VersionPolicy.MIXED,
                             final LayoutPolicy layoutPolicy = LayoutPolicy.STRICT) {
    [versionPolicy: versionPolicy, layoutPolicy: layoutPolicy]
  }

  abstract Repository createRepository(final Configuration configuration)
}
