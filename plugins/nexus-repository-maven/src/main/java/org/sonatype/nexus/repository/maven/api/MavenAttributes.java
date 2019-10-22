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
package org.sonatype.nexus.repository.maven.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * REST API model for describing maven specific repository properties.
 *
 * @since 3.next
 */
public class MavenAttributes
{
  @ApiModelProperty(value = "What type of artifacts does this repository store?",
      allowableValues = "release,snapshot,mixed",
      example = "mixed")
  @NotEmpty
  protected final String versionPolicy;

  @ApiModelProperty(value = "Validate that all paths are maven artifact or metadata paths",
      allowableValues = "strict,permissive",
      example = "strict")
  @NotEmpty
  protected final String layoutPolicy;

  @JsonCreator
  public MavenAttributes(
      @JsonProperty("versionPolicy") final String versionPolicy,
      @JsonProperty("layoutPolicy") final String layoutPolicy)
  {
    this.versionPolicy = versionPolicy;
    this.layoutPolicy = layoutPolicy;
  }

  public String getVersionPolicy() {
    return versionPolicy;
  }

  public String getLayoutPolicy() {
    return layoutPolicy;
  }
}
