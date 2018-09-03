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
/*global Ext, NX*/

/**
 * Blobstore model.
 *
 * @since 3.0
 */
Ext.define('NX.coreui.model.Blobstore', {
  extend: 'Ext.data.Model',
  idProperty: 'name',
  fields: [
    {name: 'name', type: 'string', sortType: 'asUCText'},
    {name: 'type', type: 'string', sortType: 'asUCText'},
    {name: 'isQuotaEnabled', type:'boolean' },
    {name: 'quotaType', type:'string' },
    {name: 'quotaLimit', type:'int' },
    {name: 'attributes', type: 'auto' /*object*/, defaultValue: null },
    {name: 'blobCount', type: 'int'},
    {name: 'totalSize', type: 'int'},
    {name: 'availableSpace', type: 'int'},
    {name: 'unlimited', type: 'boolean'},
    {name: 'repositoryUseCount', type: 'int'},
    {name: 'blobStoreUseCount', type: 'int'},
    {name: 'inUse', type: 'boolean'},
    {name: 'promotable', type: 'boolean'}
  ]
});
