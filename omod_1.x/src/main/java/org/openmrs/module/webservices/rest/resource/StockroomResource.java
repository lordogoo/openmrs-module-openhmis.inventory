/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * Copyright (C) OpenHMIS.  All Rights Reserved.
 */
package org.openmrs.module.webservices.rest.resource;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.inventory.ModuleSettings;
import org.openmrs.module.openhmis.inventory.api.IStockroomDataService;
import org.openmrs.module.openhmis.inventory.api.model.Stockroom;
import org.openmrs.module.openhmis.inventory.web.ModuleRestConstants;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.EmptySearchResult;
import org.openmrs.util.LocationUtility;

/**
 * REST resource representing a {@link Stockroom}.
 */
@Resource(name = ModuleRestConstants.STOCKROOM_RESOURCE, supportedClass = Stockroom.class,
        supportedOpenmrsVersions = { "1.9.*", "1.10.*", "1.11.*", "1.12.*" })
public class StockroomResource extends BaseRestMetadataResource<Stockroom> {
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = super.getRepresentationDescription(rep);
		description.addProperty("location", Representation.REF);
		description.addProperty("id");

		return description;
	}

	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = super.getCreatableProperties();
		description.addProperty("name");
		description.addProperty("location");

		return description;
	}

	@Override
	protected PageableResult doGetAll(RequestContext context) {
		if (ModuleSettings.areItemsRestrictedByLocation()) {
			//kmri location restriction
			Location locationTemp = LocationUtility.getUserDefaultLocation();
			PagingInfo pagingInfo = PagingUtil.getPagingInfoFromContext(context);
			if (locationTemp != null) {
				return new AlreadyPagedWithLength<Stockroom>(context,
				        Context.getService(IStockroomDataService.class).getStockroomsByLocation(
				            locationTemp, context.getIncludeAll(), pagingInfo),
				        pagingInfo.hasMoreResults(), pagingInfo.getTotalRecordCount());
			} else {
				return new EmptySearchResult();
			}
		} else {
			return super.doGetAll(context);
		}
	}

	@Override
	public Stockroom newDelegate() {
		return new Stockroom();
	}

	@Override
	public Class<? extends IMetadataDataService<Stockroom>> getServiceClass() {
		return IStockroomDataService.class;
	}
}
