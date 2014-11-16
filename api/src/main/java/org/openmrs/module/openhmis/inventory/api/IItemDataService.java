/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.openhmis.inventory.api;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.module.openhmis.commons.api.PagingInfo;
import org.openmrs.module.openhmis.commons.api.entity.IMetadataDataService;
import org.openmrs.module.openhmis.inventory.api.model.Category;
import org.openmrs.module.openhmis.inventory.api.model.Department;
import org.openmrs.module.openhmis.inventory.api.model.Item;
import org.openmrs.module.openhmis.inventory.api.search.ItemSearch;
import org.openmrs.module.openhmis.inventory.api.util.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface IItemDataService extends IMetadataDataService<Item> {

    @Transactional(readOnly =  true)
    @Authorized( {PrivilegeConstants.VIEW_ITEMS} )
    public List<Item> listItemsByDrugId(Integer drugId) throws APIException;

    @Transactional(readOnly =  true)
    @Authorized( {PrivilegeConstants.VIEW_ITEMS} )
    public List<Item> listItemsByConceptId(Integer conceptId) throws APIException;

    @Transactional(readOnly =  true)
    @Authorized( {PrivilegeConstants.VIEW_ITEMS} )
    public List<Item> listAllItems() throws APIException;

    @Transactional(readOnly = true)
    @Authorized( {PrivilegeConstants.VIEW_ITEMS} )
    public List<Item> getItemById(final Integer itemId) throws APIException;
    @Transactional
    @Authorized( {PrivilegeConstants.MANAGE_OPERATIONS})
    public Boolean dispenseItem(Integer itemId, Integer quantity) throws IllegalArgumentException, APIException;


    /**
	 * Gets the {@link Item} with the specified code or {@code null} if not found.
	 * @param itemCode The item code to find.
	 * @return The {@link Item} or with the specified item code or {@code null}.
	 * @throws APIException
	 * @should throw IllegalArgumentException if the item code is null
	 * @should throw IllegalArgumentException if the item code is longer than 255 characters
	 * @should return the item with the specified item code
	 * @should return null if the item code is not found
	 */
	@Transactional(readOnly =  true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	Item getItemByCode(String itemCode) throws APIException;

	/**
	 * Gets all items with the specified code or {@code null} if not found.
	 * This is needed as long as it is not mandatory for codes to be unique
	 * @param itemCode The item code to find.
	 * @return All items with the specified item code or {@code null}.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @throws APIException
	 * @should throw IllegalArgumentException if the item code is null
	 * @should throw IllegalArgumentException if the item code is longer than 255 characters
	 * @should return the item with the specified item code
	 * @should return null if the item code is not found
	 */
	@Transactional(readOnly =  true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByCode(String itemCode, boolean includeRetired) throws APIException;

	/**
	 * Gets all the items for the specified code.
	 * This is needed as long as it is not mandatory for codes to be unique
	 * @param itemCode The item code to find.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information
	 * @return All items for the specified code.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByCode(String itemCode, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all the items for the specified {@link Department}.
	 * @param department The department.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items for the specified {@link Department}.
	 * @throws APIException
	 * @should throw NullPointerException if the department is null
	 * @should return an empty list if the department has no items
	 * @should not return retired items unless specified
	 * @should return all items for the specified department
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByDepartment(Department department, boolean includeRetired) throws APIException;

	/**
	 * Gets all the items for the specified {@link Department}.
	 * @param department The department.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information
	 * @return All items for the specified {@link Department}.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByDepartment(Department department, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all the items for the specified {@link Category}.
	 * @param category The category.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items for the specified {@link Category}.
	 * @throws APIException
	 * @should throw NullPointerException if the category is null
	 * @should return an empty list if the category has no items
	 * @should not return retired items unless specified
	 * @should return all items for the specified category
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByCategory(Category category, boolean includeRetired) throws APIException;

	/**
	 * Gets all the items for the specified {@link Category}.
	 * @param category The category.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information
	 * @return All items for the specified {@link Category}.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByCategory(Category category, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all the items for the specified {@link Department} and {@link Category}.
	 * @param category The category.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items for the specified {@link Department} and {@link Category}.
	 * @throws APIException
	 * @should throw NullPointerException if the department is null
	 * @should throw NullPointerException if the category is null
	 * @should return an empty list if the department and category has no items
	 * @should not return retired items unless specified
	 * @should return all items for the specified category
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Department department, Category category, boolean includeRetired) throws APIException;

	/**
	 * Gets all the items for the specified {@link Department} and {@link Category}.
	 * @param department The department.
	 * @param category The category.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information
	 * @return All items for the specified {@link Department} and {@link Category}.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Department department, Category category, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all items in the specified {@link Department} that start with the specified name.
	 * @param department The department to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items in the specified {@link Department} that start with the specified name.
	 * @throws APIException
	 * @should throw NullPointerException if the department is null
	 * @should throw IllegalArgumentException if the name is null
	 * @should throw IllegalArgumentException if the name is empty
	 * @should throw IllegalArgumentException if the name is longer than 255 characters
	 * @should return an empty list if no items are found
	 * @should not return retired items unless specified
	 * @should return items that start with the specified name
	 * @should return items for only the specified department
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Department department, String name, boolean includeRetired) throws APIException;

	/**
	 * Gets all items in the specified {@link Department} that start with the specified name.
	 * @param department The department to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information.
	 * @return All items in the specified {@link Department} that start with the specified name.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Department department, String name, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all items in the specified {@link Category} that start with the specified name.
	 * @param category The category to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items in the specified {@link Category} that start with the specified name.
	 * @throws APIException
	 * @should throw NullPointerException if the category is null
	 * @should throw IllegalArgumentException if the name is null
	 * @should throw IllegalArgumentException if the name is empty
	 * @should throw IllegalArgumentException if the name is longer than 255 characters
	 * @should return an empty list if no items are found
	 * @should not return retired items unless specified
	 * @should return items that start with the specified name
	 * @should return items for only the specified category
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Category category, String name, boolean includeRetired) throws APIException;

	/**
	 * Gets all items in the specified {@link Category} that start with the specified name.
	 * @param category The category to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information.
	 * @return All items in the specified {@link Category} that start with the specified name.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Category category, String name, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all items in the specified {@link Department} and {@link Category} that start with the specified name.
	 * @param department The department to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @return All items in the specified {@link Department} and {@link Category} that start with the specified name.
	 * @throws APIException
	 * @should throw NullPointerException if the department is null
	 * @should throw NullPointerException if the category is null
	 * @should throw IllegalArgumentException if the name is null
	 * @should throw IllegalArgumentException if the name is empty
	 * @should throw IllegalArgumentException if the name is longer than 255 characters
	 * @should return an empty list if no items are found
	 * @should not return retired items unless specified
	 * @should return items that start with the specified name
	 * @should return items for only the specified department and category
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByDepartmentAndCategoryAndName(Department department, Category category, String name, boolean includeRetired) throws APIException;

	/**
	 * Gets all items in the specified {@link Department} and {@link Category} that start with the specified name.
	 * @param department The department to search within.
	 * @param category The category to search within.
	 * @param name The item name fragment.
	 * @param includeRetired Whether retired items should be included in the results.
	 * @param pagingInfo The paging information.
	 * @return All items in the specified {@link Department} and {@link Category} and that start with the specified name.
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItems(Department department, Category category, String name, boolean includeRetired, PagingInfo pagingInfo) throws APIException;

	/**
	 * Gets all items using the specified {@link ItemSearch} settings.
	 * @param itemSearch The item search settings.
	 * @return The items found or an empty list if no items were found.
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByItemSearch(ItemSearch itemSearch);

	/**
	 * Gets all items using the specified {@link ItemSearch} settings.
	 * @param itemSearch The item search settings.
	 * @param pagingInfo The paging information.
	 * @return The items found or an empty list if no items were found.
	 * @should throw NullPointerException if item search is null
	 * @should throw NullPointerException if item search template object is null
	 * @should return an empty list if no items are found via the search
	 * @should return items filtered by name
	 * @should return items filtered by department
	 * @should return items filtered by category
	 * @should return items filtered by concept
	 * @should return items filtered by physical inventory
	 * @should return items filtered by expiration
	 * @should return all items if paging is null
	 * @should return paged items if paging is specified
	 * @should not return retired items from search unless specified
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
	List<Item> getItemsByItemSearch(ItemSearch itemSearch, PagingInfo pagingInfo);

	/**
	 * Gets all items by {@link Concept} settings.
	 * @param concept The concept.
	 * @return The items found or an empty list if no items were found.
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
    List<Item> getItemsByConcept(Concept concept);

	/**
	 * Gets all items where {@link Concept} is null
	 * @param resultLimit Maximum size of returned items.
	 * @param excludedItemsIds Items not to be considered in the result.
	 * @return The items found or an empty list if no items were found.
	 * @should return all results if resultLimit is null
	 * @should not return items contained in excludedItemsIds
	 * @should only return items where concept is null
	 * @should only return items that are not retired
	 * @should only return items where concept suggestion was not accepted
	 */
	@Transactional(readOnly = true)
	@Authorized( {PrivilegeConstants.VIEW_ITEMS})
    List<Item> getItemsWithoutConcept(List<Integer> excludedItemsIds, Integer resultLimit);


}

