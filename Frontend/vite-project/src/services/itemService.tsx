const ITEM_SERVICE_URL = "http://localhost:8082/items";

// Define an interface for the item structure //TODO move this interface to types.tsx
export interface Item {
    itemId: number;
    itemName: string;
    itemDescription: string;
    storageUnitId: number;
}

// Define an interface for the new item structure
export interface NewItem {
    itemName: string;
    itemDescription: string;
}

// Fetch all items
export const fetchAllItems = async (): Promise<Item[]> => {
    try {
        const response = await fetch(ITEM_SERVICE_URL);
        if (!response.ok) {
            throw new Error('Network response not ok');
        }
        // parsing the respone as JSON
        const data = await response.json();
        return data;
    } catch (e) {
        console.error("Error fetching items:", e)
    }


};

// Add a new item
export const addItem = async (newItem: NewItem): Promise<Item> => {
    try {
        const response = await fetch(ITEM_SERVICE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newItem),
        });

        if (!response.ok) {
            throw new Error('Failed to add item');
        }
        const data = await response.json();
        return data;
    } catch (e){
        console.error("Failed adding item", e);
    }
};
