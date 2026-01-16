import { Card, H3, Text, StackLayout, FlexLayout, Button, Link } from '@salt-ds/core'
import { motion } from 'framer-motion'
import { AddIcon, EditIcon, PlayIcon, RefreshIcon } from '@salt-ds/icons'
import { PanelHeader } from './PanelHeader'
import './MainContent.css'

export function MainContent() {
  return (
    <StackLayout className="main-content" gap={3}>
      {/* Panel Header Bar */}
      <PanelHeader title="Dashboard Overview" />

      <FlexLayout justify="space-between" align="center" className="main-header">
        <StackLayout gap={0}>
          <H3>Workflow Builder</H3>
          <Text color="secondary">
            Configure and manage your automation workflow
          </Text>
        </StackLayout>
        
        <FlexLayout gap={1}>
          <Button appearance="bordered" sentiment="neutral">
            <RefreshIcon /> Refresh
          </Button>
          <Button appearance="bordered" sentiment="neutral">
            <EditIcon /> Edit
          </Button>
          <Button sentiment="accented">
            <PlayIcon /> Run Workflow
          </Button>
        </FlexLayout>
      </FlexLayout>

      <motion.div
        initial={{ opacity: 0, y: 10 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.3 }}
        style={{ flex: 1 }}
      >
        <Card className="content-card" style={{ '--saltCard-padding': '24px' } as React.CSSProperties}>
          <StackLayout gap={2}>
            <H3>Step Details</H3>
            <Text color="secondary">
              Select a step from the timeline to view and edit its configuration.
            </Text>
            
            <div className="empty-state">
              <motion.div
                className="empty-state-icon"
                whileHover={{ scale: 1.05 }}
                transition={{ duration: 0.2 }}
              >
                <AddIcon size={2} />
              </motion.div>
              <H3 styleAs="h4">No step selected</H3>
              <Text color="secondary">
                Click on a workflow step in the left panel to see its details here.
              </Text>
              <Link href="#" style={{ marginTop: '8px' }}>Learn more</Link>
            </div>
          </StackLayout>
        </Card>
      </motion.div>

      <FlexLayout gap={2} wrap>
        {[
          { label: 'Total Runs', value: '1,247', color: '#a855f7' },
          { label: 'Success Rate', value: '98.5%', color: 'var(--salt-accent-borderColor)' },
          { label: 'Avg Duration', value: '2.3s', color: '#22c55e' }
        ].map((stat, index) => (
          <motion.div
            key={stat.label}
            whileHover={{ scale: 1.02, y: -2 }}
            whileTap={{ scale: 0.98 }}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.3, delay: index * 0.1 }}
            style={{ flex: 1, minWidth: 150, cursor: 'pointer' }}
          >
            <Card 
              className="stat-card"
              style={{ 
                '--saltCard-padding': '16px',
                borderTopColor: stat.color 
              } as React.CSSProperties}
            >
              <StackLayout gap={1}>
                <Text styleAs="label" color="secondary">{stat.label}</Text>
                <H3>{stat.value}</H3>
              </StackLayout>
            </Card>
          </motion.div>
        ))}
      </FlexLayout>
    </StackLayout>
  )
}
