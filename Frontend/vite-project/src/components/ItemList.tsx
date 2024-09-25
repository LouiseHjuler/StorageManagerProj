import {useEffect, useState} from "react";
import {fetchAllItems} from "../services/itemService";

// TODO move this to types.tsx
export interface Item {
    itemId: number;
    itemName: string;
    itemDescription: string;
    storageUnitId: number;

}
function ItemList(){
    const [items, setItems] = useState<Item[]>([]);


    const handleFetchItems = async () => {
        try {
            const data = await fetchAllItems() as Item[];
            setItems(data);
        } catch (err) {
            console.error("Failed to fetch items");
        }
    };

    const ItemList = ({items}) => {
        if (!items){
            return <div>No items available</div>
        }
    }

    return (
        <div>
            <button onClick={handleFetchItems}> Fetch all the items</button>
            {items.length > 0 ? (
                <ul>
                    {items.map((item) => (
                        <li key={item.itemId}>
                            {item.itemName}: {item.itemDescription}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No items available</p>
            )}
        </div>
    );
}

export default ItemList;