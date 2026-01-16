import { 
  Card, 
  H3, 
  Text,
  FlexLayout, 
  ToggleButton,
  ToggleButtonGroup
} from '@salt-ds/core'
import { useState, ReactNode } from 'react'
import './PanelHeader.css'

interface PanelHeaderProps {
  title: string
  children?: ReactNode
}

export function PanelHeader({ title, children }: PanelHeaderProps) {
  const [view, setView] = useState<string>('Day')
  const [mode, setMode] = useState<string>('List')

  return (
    <Card className="panel-card">
      {/* Header */}
      <div className="panel-header">
        <FlexLayout justify="space-between" align="center">
          <H3>{title}</H3>
          
          <FlexLayout gap={2}>
            <ToggleButtonGroup
              value={view}
              onChange={(e, value) => setView(value)}
            >
              <ToggleButton value="Day">Day</ToggleButton>
              <ToggleButton value="Week">Week</ToggleButton>
              <ToggleButton value="Month">Month</ToggleButton>
            </ToggleButtonGroup>
            
            <ToggleButtonGroup
              value={mode}
              onChange={(e, value) => setMode(value)}
            >
              <ToggleButton value="List">List</ToggleButton>
              <ToggleButton value="Grid">Grid</ToggleButton>
            </ToggleButtonGroup>
          </FlexLayout>
        </FlexLayout>
      </div>

      {/* Content */}
      <div className="panel-content">
        {children || (
          <Text color="secondary">Panel content goes here</Text>
        )}
      </div>
    </Card>
  )
}
