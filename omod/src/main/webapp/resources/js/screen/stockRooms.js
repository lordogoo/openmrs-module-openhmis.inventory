curl(
	{ baseUrl: openhmis.url.resources },
	[
		openhmis.url.backboneBase + 'js/lib/jquery',
		openhmis.url.backboneBase + 'js/openhmis',
		openhmis.url.backboneBase + 'js/lib/backbone-forms',
		openhmis.url.inventoryBase + 'js/model/stockRoom',
        openhmis.url.inventoryBase + 'js/view/stockRoom',
		openhmis.url.backboneBase + 'js/view/generic'
	],
	function($, openhmis) {
		$(function() {
			var stockRoomList = $("#stockRoomList");
            var stockRoomInfo = $("#stockRoomInfo");
            var stockRoomEdit = $("#stockRoomEdit");

			// Display current stock rooms into list
            openhmis.startAddEditScreen(openhmis.StockRoom, {
				listFields: ['name', 'description'],
                listElement: stockRoomList,
                addEditViewType: openhmis.StockRoomDetailView,
                addEditElement: stockRoomInfo
			});

            // Configure Add/Edit stock room
            //addEditView.on("cancel", listView.blur);

            // Set to be dialog

            // Set detail to use tabs
			//stockRoomInfo.on("focus", $("#detailTabs").tabs());

            var stockRoomItemListView = new openhmis.GenericListView();

            stockRoomItemListView.setElement(stockRoomList);
            stockRoomList.append(stockRoomItemListView.render().el);
		});
	}
);