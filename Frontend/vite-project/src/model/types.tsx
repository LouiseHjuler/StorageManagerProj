

// This could be used to store all the interfaces, and then used in itemList or Storageunit

export interface Item {
    itemId: number;
    itemName: string;
    itemDescription: string;
}

// Define the StorageUnit interface
export interface StorageUnit {
    unitId: number;
    unitName: string;
    // unitLocation: string; //TODO delete this
}