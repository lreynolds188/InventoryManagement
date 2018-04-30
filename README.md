# InventoryManagement

SuperMart is a supermarket chain that sells a range of fresh produce, meats,
frozen food, and dry goods (items that do not need to be temperature con-
trolled). Every customer purchase at a SuperMart store is appended to the
store's weekly sales log. At the end of each week, the store manager examines
that week's sales log and registers the update to the store's capital and inven-
tory. At this point, the store's inventory needs to be replenished, so a stock
order is produced by the manager.
Stock orders are produced by checking the store's inventory for items whose
quantities are less than or equal to their reorder point, indicating that the item
must be reordered. If an item needs to be reordered, N units of that item are
added to the stock order, where N is the item's reorder amount. Finally, the
manager sends the compiled stock order to the nearest distribution centre.
Distribution centres analyze stock orders and assemble shipping manifests
to fulll a store's replenishment demand. These manifests specify a collection
of trucks and their cargo, where the total sum of all the cargo is equal to the
stock order.
Certain items must be temperature controlled throughout the entire supply
chain. Therefore, distribution centres administer both refrigerated and ordi-
nary trucks. However, the cost of a refrigerated truck is inversely exponentially
proportional to its temperature. Therefore, distribution centres must op-
timize manifests for capital expenditure by minimizing the quantity
of low temperature trucks through an optimal logistical allocation of
items.
When all trucks specied in a manifest have delivered their goods to a store,
the store registers the update to its working capital and inventory based on the
information in the manifest. The store pays for the manifest in full, where the
total cost of the manifest is the sum of the costs of the trucks and their cargo.
SuperMart has observed recent rapid growth, but its paper-based logistics
and inventory management is struggling to keep up. You have been contracted
by SuperMart to develop an application that will be deployed to all SuperMart
stores to help automate inventory management.
Distribution centres will no longer be generating their own manifests, in-
stead, manifests will now be handled entirely through each store's application.
Every time a sales log is loaded in, the manager will export a manifest and
submit it to the distribution centre. The distribution centre will then schedule
trucks according to the manifest. After all requested trucks have delivered their
cargo, the store manager will then load the manifest back into the application,
updating the store's capital and inventory.
